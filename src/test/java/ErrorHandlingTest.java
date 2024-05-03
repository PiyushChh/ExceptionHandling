import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ErrorHandlingTest {
    private final ErrorHandling errorHandling = new ErrorHandling();

    @Test
    public void testThrowIllegalArgumentException() {
        Class<Exception> expectedException = Exception.class;

        ThrowableAssert.ThrowingCallable actualException = errorHandling::handleErrorByThrowingIllegalArgumentException;

        assertThatExceptionOfType(expectedException)
                .isThrownBy(actualException);
    }

    @Test
    public void testThrowIllegalArgumentExceptionWithDetailMessage() {
        Class<IllegalArgumentException> expectedException = IllegalArgumentException.class;
        String exceptionMessage = "This is the detail message.";

        ThrowableAssert.ThrowingCallable actualException = () -> errorHandling.handleErrorByThrowingIllegalArgumentExceptionWithDetailMessage(
                exceptionMessage);

        assertThatExceptionOfType(expectedException)
                .isThrownBy(actualException)
                .withMessage(exceptionMessage);
    }

    @Test
    public void testThrowAnyCheckedException() {
        Class<Exception> expectedException = Exception.class;
        Class<RuntimeException> unExpectedException = RuntimeException.class;

        ThrowableAssert.ThrowingCallable actualException = errorHandling::handleErrorByThrowingAnyCheckedException;

        assertThatExceptionOfType(expectedException)
                .isThrownBy(actualException)
                .isNotInstanceOf(unExpectedException);
    }

    @Test
    public void testThrowAnyCheckedExceptionWithDetailMessage() {
        String errorMessage = "This is the detail message.";
        Class<Exception> expectedException = Exception.class;
        Class<RuntimeException> unexpectedException = RuntimeException.class;

        ThrowableAssert.ThrowingCallable actualException = () -> errorHandling.handleErrorByThrowingAnyCheckedExceptionWithDetailMessage(
                errorMessage);

        assertThatExceptionOfType(expectedException)
                .isThrownBy(actualException)
                .isNotInstanceOf(unexpectedException)
                .withMessage(errorMessage);
    }

    @Test
    public void testThrowAnyUncheckedException() {
        Class<RuntimeException> expectedException = RuntimeException.class;

        ThrowableAssert.ThrowingCallable actualException = errorHandling::handleErrorByThrowingAnyUncheckedException;

        assertThatExceptionOfType(expectedException)
                .isThrownBy(actualException);
    }

    @Test
    public void testThrowAnyUncheckedExceptionWithDetailMessage() {
        Class<RuntimeException> expectedException = RuntimeException.class;
        String exceptionMessage = "This is the detail message.";

        ThrowableAssert.ThrowingCallable actualException = () -> errorHandling.handleErrorByThrowingAnyUncheckedExceptionWithDetailMessage(
                exceptionMessage);

        assertThatExceptionOfType(expectedException)
                .isThrownBy(actualException)
                .withMessage(exceptionMessage);
    }

    @Test
    public void testThrowCustomCheckedException() {
        Class<CustomCheckedException> expectedException = CustomCheckedException.class;

        ThrowableAssert.ThrowingCallable actualException = errorHandling::handleErrorByThrowingCustomCheckedException;

        assertThatExceptionOfType(expectedException)
                .isThrownBy(actualException);
    }

    @Test
    public void testThrowCustomCheckedExceptionWithDetailMessage() {
        Class<CustomCheckedException> expectedException = CustomCheckedException.class;
        String exceptionMessage = "This is the detail message.";

        ThrowableAssert.ThrowingCallable actualException = () -> errorHandling.handleErrorByThrowingCustomCheckedExceptionWithDetailMessage(
                exceptionMessage);

        assertThatExceptionOfType(expectedException)
                .isThrownBy(actualException)
                .withMessage(exceptionMessage);
    }

    @Test
    public void testThrowCustomUncheckedException() {
        Class<CustomUncheckedException> expectedException = CustomUncheckedException.class;

        ThrowableAssert.ThrowingCallable actualException = errorHandling::handleErrorByThrowingCustomUncheckedException;

        assertThatExceptionOfType(expectedException)
                .isThrownBy(actualException);
    }

    @Test
    public void testThrowCustomUncheckedExceptionWithDetailMessage() {
        String exceptionMessage = "This is the detail message.";
        Class<CustomUncheckedException> expectedException = CustomUncheckedException.class;

        ThrowableAssert.ThrowingCallable actualException = () -> errorHandling.handleErrorByThrowingCustomUncheckedExceptionWithDetailMessage(
                exceptionMessage);

        assertThatExceptionOfType(expectedException)
                .isThrownBy(actualException)
                .withMessage(exceptionMessage);
    }

    @Test
    public void testReturnOptionalInstance() {
        String validInteger = "1";
        Optional<Integer> successfulResult = errorHandling.handleErrorByReturningOptionalInstance(validInteger);
        int expectedValue = 1;

        assertThat(successfulResult).isPresent().hasValue(expectedValue);

        Optional<Integer> failureResult = errorHandling.handleErrorByReturningOptionalInstance("a");
        assertThat(failureResult).isNotPresent();
    }
}
