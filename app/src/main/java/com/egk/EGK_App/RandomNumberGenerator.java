package com.egk.EGK_App;

import java.util.LinkedList;
import java.util.Random;

/**
 * a RNG avoiding doublings
 * @author Gereon
 * @version 1
 * @see Random
 */
public class RandomNumberGenerator {

    /**
     * state min, max and generated numbers get stored into
     */
    private State state = new State();

    /**
     * generates random number and checks for previous generations to avoid doubling
     * @param min smallest possible outcome (inclusive)
     * @param max biggest possible outcome (inclusive)
     * @return random number not yet generated
     */
    public int nextRandomNumber(int min, int max) {
        if(state.boundsChanged(min, max)) state.reset(min, max);
        else if(state.isFull()) state.reset();

        Random random = new Random();
        int randomNumber;
        do randomNumber = min + random.nextInt(max - min + 1); while(state.generatedNumbers.contains(randomNumber));
        state.generatedNumbers.add(randomNumber);
        return randomNumber;
    }

    /**
     * Class to store min, max & generated numbers in
     * @author Gereon
     * @version 1
     */
    private static class State {

        /**
         * min and max values
         */
        public int min, max = 0;

        /**
         * already generated Numbers
         */
        public LinkedList<Integer> generatedNumbers;

        /**
         * creates new State, initializes generatedNumbers-List to an empty List
         */
        public State() {
            generatedNumbers = new LinkedList<>();
        }

        /**
         * creates new State, initializes min and max to params & generatedNumbers-List to an empty List
         * @param min min value
         * @param max max value
         */
        public State(int min, int max) {
            this.min = min;
            this.max = max;
            generatedNumbers = new LinkedList<>();
        }

        /**
         * creates new State
         * @param min min value
         * @param max max value
         * @param generatedNumbers already generated Numbers
         */
        public State(int min, int max, LinkedList<Integer> generatedNumbers) {
            this.min = min;
            this.max = max;
            this.generatedNumbers = generatedNumbers;
        }

        /**
         * checks wheter everything between min & max was already generated
         * @return true if everything was generated
         */
        public boolean isFull() {
            return generatedNumbers.size() > max - min;
        }

        /**
         * checks wheter the min & max are up to date with params
         * @param min input min
         * @param max input max
         * @return true if at least one value changed
         */
        public boolean boundsChanged(int min, int max) {
            return this.min != min || this.max != max;
        }

        /**
         * sets generatedNumbers to an empty List
         */
        public void reset() {
            generatedNumbers = new LinkedList<>();
        }

        /**
         * sets generatedNumbers to an empty List & changes min and max value
         * @param min min value
         * @param max max value
         */
        public void reset(int min, int max) {
            this.min = min;
            this.max = max;
            reset();
        }
    }
}
