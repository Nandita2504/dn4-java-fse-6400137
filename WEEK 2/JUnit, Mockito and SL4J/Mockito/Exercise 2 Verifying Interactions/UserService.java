public class UserService {
    private UserRepository userRepo;
    private NotificationService notifier;

    public UserService(UserRepository repo, NotificationService notifier) {
        this.userRepo = repo;
        this.notifier = notifier;
    }

    public void notifyUser(String userId, String message) {
        User user = userRepo.findUserById(userId);
        if (user != null) {
            notifier.sendNotification(user, message);
        }
    }
}
