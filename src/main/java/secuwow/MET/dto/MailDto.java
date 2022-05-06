package secuwow.MET.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Map;

@Getter @Setter
public class MailDto {

    private String                    fromMailName;

    private String                    fromMailAddr;

    private String                    mailTitle;

    private String                    mailContent;

    private File                      attachFile;

    private String                    attachFilePath;

    private String                    mailContentFilePath;

    private boolean 				  useSSL;

    private boolean 				  useAuth;

    private Map<String, String>       toMailUserMap;

    private Map<String, String>       ccMailUserMap;

    private String                    smtpHost;

    private String                    smtpPort;

    private String 					  authUserId;

    private String 					  authUserPass;

    private Map<String, String>       replaceWord;

}
