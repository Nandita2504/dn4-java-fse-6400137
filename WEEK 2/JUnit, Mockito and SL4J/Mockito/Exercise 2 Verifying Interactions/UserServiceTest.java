import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Test
    void testNotifyUser_verifiesInteraction() {
        // Arrange
        UserRepository mockRepo = mock(UserRepository.class);
        NotificationService mockNotifier = mock(NotificationService.class);
        User user = new User("101", "Grishma");

        when(mockRepo.findUserById("101")).thenReturn(user);

        UserService service = new UserService(mockRepo, mockNotifier);

        // Act
        service.notifyUser("101", "Hello!");

        // Assert - VERIFY
        verify(mockRepo).findUserById("101");
        verify(mockNotifier).sendNotification(user, "Hello!");
    }
}
