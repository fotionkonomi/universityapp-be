package al.edu.fti.softwareengineering.universityappbe.core.business.common;

/**
 * Message constants for different messages
 */
public class MessageConstants {
    public static final String MSG_ENTITY_NOT_FOUND_EXCEPTION = "global.entity.not_found.exception";
    public static final String MSG_EMAIL_EXISTS = "user.email.violation";
    public static final String MSG_USER_NOT_FOUND_EXCEPTION = "user.not_found_exception";
    public static final String MSG_COMMENT_EMPTY_EXCEPTION = "comment.empty";

    public static final String MSG_FRIENDSHIP_ALREADY_EXISTS = "friendship.already.exists";
    public static final String MSG_FRIENDSHIP_SAME_USER = "friendship.sameUser";
    public static final String MSG_FRIENDSHIP_NOT_FOUND = "friendship.notFound";

    // Notification contents
    public static final String MSG_NOTIFICATION_FRIENDSHIP_REQUEST = "notification.friendship.request";
    public static final String MSG_NOTIFICATION_FRIENDSHIP_REQUEST_ACCEPT = "notification.friendship.request.accept";
    public static final String MSG_NOTIFICATION_FRIENDSHIP_REQUEST_DECLINED = "notification.friendship.request.declined";
    public static final String MSG_NOTIFICATION_POST = "notification.post";
    public static final String MSG_NOTIFICATION_COURSE_START = "notification.course.start";
}
