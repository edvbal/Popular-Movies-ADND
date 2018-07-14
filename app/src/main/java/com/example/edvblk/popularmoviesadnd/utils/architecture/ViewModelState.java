package com.example.edvblk.popularmoviesadnd.utils.architecture;

public class ViewModelState {
    public final class LoadingState extends ViewModelState {
    }

    public static final class ErrorState extends ViewModelState {
        private final String errorMessage;

        public ErrorState(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
