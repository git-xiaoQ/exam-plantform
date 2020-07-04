package exam.blankquizContext.domian.blankquiz;

import exam.blankquizContext.domain.model.blankquiz.BlankQuiz;
import exam.blankquizContext.domain.model.blankquiz.BlankQuizId;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BlankQuiz.class)
public class BlankQuizTest {
    @Mock
    BlankQuizId blankQuizId;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_create_blank_quiz_successfully_when_get_score_80() throws Exception {

        BlankQuiz expectedBlankQuiz = PowerMockito.mock(BlankQuiz.class);
        when(expectedBlankQuiz.getBlankQuizId()).thenReturn(blankQuizId);

        BlankQuiz spy = PowerMockito.spy(expectedBlankQuiz);
        PowerMockito.doNothing().when(spy, "setCreatedTime",LocalDateTime.now());
        final String teacherId = "teacher-6b35fdd8-31de-4af4-9420-3331058260c5";

        mockStatic(exam.blankquizContext.domain.model.blankquiz.BlankQuiz.class);
        when(BlankQuiz.create(blankQuizId,teacherId,"content","referenceAnswer",80)).thenReturn(expectedBlankQuiz);
        BlankQuiz blankQuiz = BlankQuiz.create(blankQuizId, teacherId, "content", "referenceAnswer", 80);
        assertThat(blankQuiz, is(expectedBlankQuiz));

    }
}
