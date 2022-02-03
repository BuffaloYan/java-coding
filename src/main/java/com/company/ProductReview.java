package com.company;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;



class Result {

    /*
     * Complete the 'fiveStarReviews' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY productRatings
     *  2. INTEGER ratingThreshold
     */
    static class ProductRating implements Comparable {
        int review5;
        int total;
        float ratio;

        public void add5Star() {
            this.review5++;
            this.total++;
            this.ratio = this.review5 * 1.0f / this.total;
        }

        public ProductRating(int r, int t) {
            this.review5 = r;
            this.total = t;
            this.ratio = r * 1.0f / t;
        }

        public int compareTo(Object obj) {
            return - Float.compare(this.ratio, ((ProductRating)obj).ratio);
        }
    }

    public static int fiveStarReviews(List<List<Integer>> productRatings, int ratingThreshold) {
        List<ProductRating> list = new ArrayList<>();
        for(List<Integer> rating: productRatings) {
            list.add( new ProductRating(rating.get(0), rating.get(1)));
        }

        // sort product review by ratio descending order
        Collections.sort(list);

        float totalRatio = 0f;
        int numOfProduct = productRatings.size();
        // calculate current ratio
        for(ProductRating p: list) {
            totalRatio += p.ratio;
        }

        // target ratio, not averaged
        float targetRatio = numOfProduct * ratingThreshold / 100f;
        float targetSingleRatio = ratingThreshold / 100f;
        int numOfProductToAdd = 0;
        for(ProductRating p: list) {
            if (p.ratio >= targetSingleRatio) {
                // already meet target for this product
                continue;
            }

            float startingRatio = p.ratio;
            // add 5 star reviews for current product
            int i = p.review5;
            while(p.ratio < targetSingleRatio) {
                numOfProductToAdd++;
                p.add5Star();
            }

            totalRatio = totalRatio - startingRatio + p.ratio;
            if (totalRatio >= targetRatio) {
                break;
            }


        }

        return numOfProductToAdd;

    }

}

public class ProductReview {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int productRatingsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int productRatingsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> productRatings = new ArrayList<>();

        for (int i = 0; i < productRatingsRows; i++) {
            String[] productRatingsRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> productRatingsRowItems = new ArrayList<>();

            for (int j = 0; j < productRatingsColumns; j++) {
                int productRatingsItem = Integer.parseInt(productRatingsRowTempItems[j]);
                productRatingsRowItems.add(productRatingsItem);
            }

            productRatings.add(productRatingsRowItems);
        }

        int ratingThreshold = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.fiveStarReviews(productRatings, ratingThreshold);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
