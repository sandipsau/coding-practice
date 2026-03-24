import java.util.*;
import java.util.stream.Collectors;

/**
 * JobBoard Interview Question - Java Version
 * 
 * Implement the missing methods in the JobBoard class.
 * The jobs are initially sorted by view count (highest first).
 * 
 * Methods to implement:
 * - ingestJobs()
 * - getNextJob(String jobId)
 * - getPreviousJob(String jobId) 
 * - viewJob(String jobId) - should increment view count and re-sort
 * - searchJobs(String keyword) - returns list of job IDs matching keyword
 * - searchTopJob(String keyword) - returns top job matching keyword
 * - searchTopJob(List<String> keywords) - returns top job matching any keyword
 */

class JobBoard {
    private final List<Map<String, Object>> jobData;            // original list (insertion order)
    private final Map<String, Map<String, Object>> jobById;      // id -> job
    private final Map<String, Integer> indexById;                // id -> insertion index
    private final List<String> orderedIds;                       // next/prev order

    public JobBoard(List<Map<String, Object>> jobData) {
        this.jobData = (jobData == null) ? new ArrayList<>() : new ArrayList<>(jobData);

        this.jobById = new HashMap<>();
        this.indexById = new HashMap<>();

        for (int i = 0; i < this.jobData.size(); i++) {
            Map<String, Object> job = this.jobData.get(i);
            String id = extractId(job);
            if (id == null) continue;

            // If duplicate ids exist, keep the first one.
            jobById.putIfAbsent(id, job);
            indexById.putIfAbsent(id, i);
        }

        // Decide ordering for next/previous:
        // If all ids are numeric -> sort by numeric value; else -> insertion order.
        List<String> ids = new ArrayList<>(jobById.keySet());
        boolean allNumeric = ids.stream().allMatch(JobBoard::isNumeric);

        if (allNumeric) {
            ids.sort(Comparator.comparingLong(Long::parseLong));
        } else {
            ids.sort(Comparator.comparingInt(id -> indexById.getOrDefault(id, Integer.MAX_VALUE)));
        }
        this.orderedIds = ids;
        
    }
    
    public String getNextJob(String jobId) {
        if (jobId == null) return null;
        int idx = orderedIds.indexOf(jobId);
        if (idx < 0) return null;
        if (idx + 1 >= orderedIds.size()) return null;
        return orderedIds.get(idx + 1);
    }

    public String getPreviousJob(String jobId) {
        if (jobId == null) return null;
        int idx = orderedIds.indexOf(jobId);
        if (idx <= 0) return null;
        return orderedIds.get(idx - 1);
    }

    public Map<String, Object> viewJob(String jobId) {
        if (jobId == null) return null;
        Map<String, Object> job = jobById.get(jobId);
        if (job == null) return null;

        // Return a shallow copy so callers don’t mutate internal data accidentally.
        return new HashMap<>(job);
    }
    
    public List<String> searchJobs(String keyword) {
        if (keyword == null) return Collections.emptyList();
        String kw = keyword.trim().toLowerCase();
        if (kw.isEmpty()) return Collections.emptyList();

        // Return ids in the same next/prev order
        List<String> result = new ArrayList<>();
        for (String id : orderedIds) {
            Map<String, Object> job = jobById.get(id);
            if (job != null && containsKeyword(job, kw)) {
                result.add(id);
            }
        }
        return result;
    }

    public Map<String, Object> searchTopJob(String keyword) {
        if (keyword == null) return null;
        String kw = keyword.trim().toLowerCase();
        if (kw.isEmpty()) return null;

        String bestId = null;
        int bestScore = 0;

        for (String id : orderedIds) {
            Map<String, Object> job = jobById.get(id);
            int score = scoreJob(job, List.of(kw));
            if (score > bestScore) {
                bestScore = score;
                bestId = id;
            }
        }

        if (bestId == null) return null;
        return new HashMap<>(jobById.get(bestId));
    }

    public Map<String, Object> searchTopJob(List<String> keywords) {
        if (keywords == null) return null;

        List<String> kws = keywords.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        if (kws.isEmpty()) return null;

        String bestId = null;
        int bestScore = 0;

        for (String id : orderedIds) {
            Map<String, Object> job = jobById.get(id);
            int score = scoreJob(job, kws);
            if (score > bestScore) {
                bestScore = score;
                bestId = id;
            }
        }

        if (bestId == null) return null;
        return new HashMap<>(jobById.get(bestId));
    }

        // ----------------- helpers -----------------

    private static String extractId(Map<String, Object> job) {
        if (job == null) return null;
        Object idVal = job.get("id");
        if (idVal == null) return null;
        return String.valueOf(idVal);
    }

    private static boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0 && (c == '+' || c == '-')) continue;
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static boolean containsKeyword(Map<String, Object> job, String keywordLower) {
        if (job == null) return false;
        for (Object v : job.values()) {
            if (v instanceof String) {
                String s = ((String) v).toLowerCase();
                if (s.contains(keywordLower)) return true;
            }
        }
        return false;
    }

    private static int scoreJob(Map<String, Object> job, List<String> keywordsLower) {
        if (job == null) return 0;

        int score = 0;
        for (Object v : job.values()) {
            if (!(v instanceof String)) continue;
            String text = ((String) v).toLowerCase();
            for (String kw : keywordsLower) {
                score += countOccurrences(text, kw);
            }
        }
        return score;
    }

    private static int countOccurrences(String text, String sub) {
        if (text == null || sub == null || sub.isEmpty()) return 0;
        int count = 0;
        int from = 0;
        while (true) {
            int idx = text.indexOf(sub, from);
            if (idx < 0) break;
            count++;
            from = idx + sub.length();
        }
        return count;
    }
}

class Solution {
    private static List<Map<String, Object>> INPUT = Arrays.asList(
        Map.of("id", "1", "title", "sit ullamco anim incididunt irure", 
                "description", "non ipsum labore ut laboris nulla eiusmod ad proident Web Web Web", "views", 10),
        Map.of("id", "2", "title", "incididunt ut veniam id commodo", 
                "description", "adipisicing veniam aliquip eiusmod labore adipisicing ipsum ea incididunt pariatur Web Web Dev Dev Dev Data Data Data", "views", 8),
        Map.of("id", "3", "title", "proident id esse do voluptate", 
                "description", "amet fugiat exercitation amet consequat esse ex excepteur cillum reprehenderit Web", "views", 6),
        Map.of("id", "4", "title", "eiusmod occaecat deserunt exercitation adipisicing", 
                "description", "in dolore reprehenderit esse ea aliquip ipsum laborum consequat Lorem", "views", 4),
        Map.of("id", "5", "title", "officia irure sit ex non", 
                "description", "ea ea aute tempor et nostrud esse est qui proident", "views", 2),
        Map.of("id", "6", "title", "officia irure sit ex non - CA", 
                "description", "ea ea aute tempor et nostrud esse est qui proident CA ", "views", 2),
        Map.of("id", "7", "title", "officia irure sit ex non - AU", 
                "description", "ea ea aute tempor et nostrud esse est qui proident AU", "views", 2)
    );
        
    public static void main(String[] args) {
        JobBoard smallJobBoard = new JobBoard(INPUT);

        runTests(smallJobBoard);    
    }

    /** 
     * 
     * 
     * THERE'S ONLY TESTING CODE BELOW THIS POINT. PLEASE DO NOT EDIT.
     * 
     * 
     */
    private static boolean runL1Tests = false;
    private static boolean runL2Tests = false;
    private static boolean runL3Tests = false;
    
    private static void runTests(JobBoard smallJobBoard) {
        String jobId1 = (String) INPUT.get(0).get("id");
        String jobId2 = (String) INPUT.get(1).get("id");
        String jobId3 = (String) INPUT.get(2).get("id");
        String jobId4 = (String) INPUT.get(3).get("id");
        String jobId5 = (String) INPUT.get(4).get("id");
        String jobId6 = (String) INPUT.get(5).get("id");
        String jobId7 = (String) INPUT.get(6).get("id");
        
        if (runL1Tests) {
            System.out.println("Test 1.1: " + Objects.equals(smallJobBoard.getNextJob(jobId1), jobId2));
            System.out.println("Test 1.2: " + Objects.equals(smallJobBoard.getNextJob(jobId2), jobId3));
            System.out.println("Test 1.3: " + Objects.equals(smallJobBoard.getNextJob(jobId3), jobId4));
            System.out.println("Test 1.4: " + Objects.equals(smallJobBoard.getNextJob(jobId4), jobId5));
            System.out.println("Test 2.1: " + Objects.equals(smallJobBoard.getPreviousJob(jobId5), jobId4));
            System.out.println("Test 2.2: " + Objects.equals(smallJobBoard.getPreviousJob(jobId4), jobId3));
            System.out.println("Test 2.3: " + Objects.equals(smallJobBoard.getPreviousJob(jobId3), jobId2));
            System.out.println("Test 2.4: " + Objects.equals(smallJobBoard.getPreviousJob(jobId2), jobId1));
        }
        
        if (runL2Tests) {
            Map<String, Object> job = null;
            for (int i = 0; i < 10; i++) {
                job = smallJobBoard.viewJob(jobId1);
            }
            System.out.println("Test 3.1: " + Objects.equals(job.get("id"), jobId1));
            System.out.println("Test 3.2: " + Objects.equals(job.get("views"), 20));
            
            job = null;
            for (int i = 0; i < 20; i++) {
                job = smallJobBoard.viewJob(jobId2);
            }
            System.out.println("Test 3.3: " + Objects.equals(job.get("id"), jobId2));
            System.out.println("Test 3.4: " + Objects.equals(job.get("views"), 28));
            System.out.println("Test 3.5: " + Objects.equals(smallJobBoard.getNextJob(jobId2), jobId1));
            System.out.println("Test 3.6: " + Objects.equals(smallJobBoard.getNextJob(jobId1), jobId3));
            System.out.println("Test 3.7: " + Objects.equals(smallJobBoard.getPreviousJob(jobId1), jobId2));
            System.out.println("Test 3.8: " + Objects.equals(smallJobBoard.getPreviousJob(jobId3), jobId1));
            
            job = smallJobBoard.viewJob(jobId7);
            System.out.println("Test 3.9: " + Objects.equals(job.get("id"), jobId7));
            System.out.println("Test 3.10: " + Objects.equals(job.get("views"), 3));
            System.out.println("Test 3.11: " + Objects.equals(smallJobBoard.getNextJob(jobId7), jobId5));
            System.out.println("Test 3.12: " + Objects.equals(smallJobBoard.getNextJob(jobId4), jobId7));
            System.out.println("Test 3.13: " + Objects.equals(smallJobBoard.getPreviousJob(jobId5), jobId7));
            System.out.println("Test 3.14: " + Objects.equals(smallJobBoard.getPreviousJob(jobId7), jobId4));
            
            List<String> jobIds = smallJobBoard.searchJobs("Web");
            System.out.println("Test 4.1: " + (jobIds.size() == 3));
            List<String> expectedIds = Arrays.asList("1", "2", "3");
            Collections.sort(jobIds);
            Collections.sort(expectedIds);
            System.out.println("Test 4.2: " + Objects.equals(jobIds, expectedIds));
        }
        
        if (runL3Tests) {
            Map<String, Object> job = smallJobBoard.searchTopJob("Web");
            System.out.println("Test 5.1: " + Objects.equals(job.get("id"), "1"));
            
            job = smallJobBoard.searchTopJob(Arrays.asList("Web", "Dev", "Data"));
            System.out.println("Test 5.2: " + Objects.equals(job.get("id"), "2"));
        }
    }
}