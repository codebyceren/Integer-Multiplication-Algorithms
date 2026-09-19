import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeightedCompletionTimes {

    public static class Job {
        long weight;
        long length;
        long difference;

        public Job(long weight, long length) {
            this.weight = weight;
            this.length = length;
            this.difference = weight - length;
        }
    }

    public static void main(String[] args) {

        List<Job> jobs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader("data/jobs.txt"))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");

                long weight = Long.parseLong(parts[0]);
                long length = Long.parseLong(parts[1]);

                Job job = new Job(weight, length);
                jobs.add(job);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Question 1: Sort by decreasing (weight - length)
        // Tie: higher weight first
        List<Job> jobsQ1 = new ArrayList<>(jobs);

        jobsQ1.sort((job1, job2) -> {
            if (job1.difference != job2.difference) {
                return Long.compare(job2.difference, job1.difference);
            } else {
                return Long.compare(job2.weight, job1.weight);
            }
        });

        long completionTimeQ1 = 0;
        long weightedCompletionTimeQ1 = 0;

        for (Job job : jobsQ1) {
            completionTimeQ1 += job.length;
            weightedCompletionTimeQ1 += job.weight * completionTimeQ1;
        }

        System.out.println("Question 1: " + weightedCompletionTimeQ1);


        // Question 2: Sort by decreasing weight / length ratio
        List<Job> jobsQ2 = new ArrayList<>(jobs);

        jobsQ2.sort((job1, job2) -> {
            long left = job1.weight * job2.length;
            long right = job2.weight * job1.length;

            return Long.compare(right, left);
        });

        long completionTimeQ2 = 0;
        long weightedCompletionTimeQ2 = 0;

        for (Job job : jobsQ2) {
            completionTimeQ2 += job.length;
            weightedCompletionTimeQ2 += job.weight * completionTimeQ2;
        }

        System.out.println("Question 2: " + weightedCompletionTimeQ2);
    }
}