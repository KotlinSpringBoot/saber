package com.light.saber.textrank;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TextRank关键词提取
 *
 * @author hankcs
 */
public class TextRankKeyword {
    public static final int MAX_KEY_WORDS = 7;
    /**
     * 阻尼系数（ＤａｍｐｉｎｇＦａｃｔｏｒ），一般取值为0.85
     */
    static final float d = 0.618f;
    /**
     * 最大迭代次数
     */
    static final int max_iter = 2000;
    static final float min_diff = 0.001f;

    public TextRankKeyword() {
        // jdk bug : Exception in thread "main" java.lang.IllegalArgumentException: Comparison method violates its general contract!
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
    }


    public String getKeyword(String title, String content) {
        List<Term> termList = HanLP.segment(title + content);
        List<String> wordList = new ArrayList<String>();
        for (Term t : termList) {
            if (shouldInclude(t)) {
                wordList.add(t.word);
            }
        }
        Map<String, Set<String>> words = new HashMap<String, Set<String>>();
        Queue<String> que = new LinkedList<String>();
        for (String w : wordList) {
            if (!words.containsKey(w)) {
                words.put(w, new HashSet<String>());
            }
            que.offer(w);
            if (que.size() > 5) {
                que.poll();
            }

            for (String w1 : que) {
                for (String w2 : que) {
                    if (w1.equals(w2)) {
                        continue;
                    }

                    words.get(w1).add(w2);
                    words.get(w2).add(w1);
                }
            }
        }
        Map<String, Float> score = new HashMap<String, Float>();
        for (int i = 0; i < max_iter; ++i) {
            Map<String, Float> m = new HashMap<String, Float>();
            float max_diff = 0;
            for (Map.Entry<String, Set<String>> entry : words.entrySet()) {
                String key = entry.getKey();
                Set<String> value = entry.getValue();
                m.put(key, 1 - d);
                for (String other : value) {
                    int size = words.get(other).size();
                    if (key.equals(other) || size == 0) continue;
                    m.put(key, m.get(key) + d / size * (score.get(other) == null ? 0 : score.get(other)));
                }
                max_diff = Math.max(max_diff, Math.abs(m.get(key) - (score.get(key) == null ? 0 : score.get(key))));
            }
            score = m;
            if (max_diff <= min_diff) break;
        }
        List<Map.Entry<String, Float>> entryList = new ArrayList<Map.Entry<String, Float>>(score.entrySet());
        Collections.sort(entryList, (o1, o2) -> (o1.getValue() - o2.getValue() > 0 ? -1 : 1));

        List<Map.Entry<String, Float>> list = entryList.stream().filter(w -> w.getKey().length() > 1).collect(Collectors.toList());
        String result = "";
        int nKeyword = MAX_KEY_WORDS > list.size() ? list.size() : MAX_KEY_WORDS;
        for (int i = 0; i < nKeyword; ++i) {
            result += list.get(i).getKey() + ';';
        }
        System.out.print(result);
        return result;
    }

    /**
     * 是否应当将这个term纳入计算，词性属于名词、动词、副词、形容词
     *
     * @param term
     * @return 是否应当
     */
    public boolean shouldInclude(Term term) {
        return CoreStopWordDictionary.shouldInclude(term);
    }
}
