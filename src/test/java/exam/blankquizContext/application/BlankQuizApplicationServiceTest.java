package exam.blankquizContext.application;

import exam.blankquizContext.domain.model.blankquiz.BlankQuiz;
import exam.blankquizContext.domain.model.blankquiz.BlankQuizId;
import exam.blankquizContext.domain.model.blankquiz.BlankQuizRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BlankQuiz.class)
public class BlankQuizApplicationServiceTest {
    @Mock
    BlankQuizRepository blankQuizRepository;

    @Mock
    BlankQuizId blankQuizId;

    @InjectMocks
    BlankQuizApplicationService blankQuizApplicationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_create_blank_quiz_successfully_when_get_create_blankQuiz_command() throws Exception {
        CreateBlankQuizCommand createBlankQuizCommand = mock(CreateBlankQuizCommand.class);
        final String teacherId = "teacher-6b35fdd8-31de-4af4-9420-3331058260c5";
        when(createBlankQuizCommand.getTeacherId()).thenReturn(teacherId);
        when(createBlankQuizCommand.getQuestion()).thenReturn("question");
        when(createBlankQuizCommand.getReferenceAnswer()).thenReturn("referenceAnswer");
        when(createBlankQuizCommand.getScore()).thenReturn(80);

        BlankQuiz expectedBlankQuiz = PowerMockito.mock(BlankQuiz.class);
        PowerMockito.when(expectedBlankQuiz.getBlankQuizId()).thenReturn(blankQuizId);

        mockStatic(BlankQuiz.class);
        when(BlankQuizId.nextBlankQuizId()).thenReturn(blankQuizId);
        PowerMockito.when(BlankQuiz.create(blankQuizId,teacherId,"content","referenceAnswer",80)).thenReturn(expectedBlankQuiz);

        doNothing().when(blankQuizRepository).save(expectedBlankQuiz);

        BlankQuizId quiz = blankQuizApplicationService.createQuiz(createBlankQuizCommand);

        assertThat(quiz, is(expectedBlankQuiz));

    }


}
