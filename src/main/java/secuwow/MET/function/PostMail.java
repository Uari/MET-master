package secuwow.MET.function;

import lombok.extern.slf4j.Slf4j;
import secuwow.MET.dto.MailDto;
import secuwow.MET.dto.ScenarioDto;
import secuwow.MET.dto.UserDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.activation.DataHandler;
import javax.mail.Multipart;
import javax.mail.Message;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

@Slf4j
public class PostMail {

    private MailDto mailDto = new MailDto();

    private final String KEY_SMTP_HOST          = "mail.smtp.host";

    private final String KEY_SMTP_PORT          = "mail.smtp.port";

    private final String KEY_SMTP_SSL_USE       = "mail.smtp.ssl.enable";

    private final String KEY_SMTP_SSL_AUTH_HOST = "mail.smtp.ssl.trust";

    private final String KEY_SMTP_AUTH_USE      = "mail.smtp.auth";

    //option = 1 -> test
    public void postMail(String EncryptionCode, ScenarioDto scenarioDto, UserDto userDto) {
        mailDto.setFromMailName(scenarioDto.getSendMailUser());
        mailDto.setFromMailAddr(scenarioDto.getSendMailAddr());
        mailDto.setMailTitle(scenarioDto.getMailTitle());
        mailDto.setMailContent(scenarioDto.getMailContent());
        mailDto.setAttachFilePath(scenarioDto.getFilePath());
        mailDto.setUseSSL(true);
        mailDto.setUseAuth(true);
        mailDto.setSmtpHost("smtps.hiworks.com");
        mailDto.setSmtpPort("465");
        mailDto.setAuthUserId("wowmail@secuwow.com");
        mailDto.setAuthUserPass("secuwowmail!@!");
        Calendar calendar = Calendar.getInstance();

        String displayAddr = InitPage.HTTP_URL + "/displayUpdate?";
        String clickAddr = InitPage.HTTP_URL + "/clickUpdate?";
        String downAddr = InitPage.HTTP_URL + "/downUpdate?";

        if(EncryptionCode == "test")
        {
            displayAddr += "test";
            clickAddr += "test";
            downAddr += "test";
        }else
        {
            displayAddr += EncryptionCode;
            clickAddr += EncryptionCode;
            downAddr += EncryptionCode;
        }

        String id = userDto.getUserAddr().split("@")[0];

        Map<String, String> toMailUserMap = new HashMap<String, String>();

        Map<String, String> replaceWords = new HashMap<String, String>();

        toMailUserMap.put(userDto.getUserName(), userDto.getUserAddr());

        replaceWords.put("${LINK}", displayAddr);
        replaceWords.put("${HREF}", clickAddr);
        replaceWords.put("${DOWN}", downAddr);
        replaceWords.put("${NAME}", userDto.getUserName());
        replaceWords.put("${ID}", id);

        replaceWords.put("${ID_MASK}", id.substring(0, id.length() >= 2 ? 2 : id.length()) + new String(new char[id.length() >= 2 ? id.length() - 2 : 0]).replace("\0", "*"));

        replaceWords.put("${MAIL}", userDto.getUserAddr());
        replaceWords.put("${DATE}", new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        replaceWords.put("${YEAR}", Integer.toString(calendar.get(Calendar.YEAR)));
        replaceWords.put("${MONTH}", Integer.toString(calendar.get(Calendar.MONTH) + 1));
        replaceWords.put("${URL}", InitPage.HTTP_URL);

        replaceWords.put("${ENG_MONTH}", DateUtil.getEnglishMonthName(calendar.get(Calendar.MONTH) + 1));

        replaceWords.put("${DAY}", Integer.toString(calendar.get(Calendar.DATE)));
        replaceWords.put("${ENG_DAY}", DateUtil.getEnglishDayName(calendar.get(Calendar.DAY_OF_WEEK)));

        if (calendar.get(Calendar.HOUR_OF_DAY) < 13) {
            replaceWords.put("${OCLOCK}", "오전");
        } else {
            replaceWords.put("${OCLOCK}", "오후");
        }

        replaceWords.put("${HOUR}", Integer.toString(calendar.get(Calendar.HOUR)));
        replaceWords.put("${MINUTE}", Integer.toString(calendar.get(Calendar.MINUTE)));

        mailDto.setReplaceWord(replaceWords);
        mailDto.setToMailUserMap(toMailUserMap);


        log.debug("Mail Send Start");


        FileInputStream fis = null;

        InputStream is = null;

        Multipart mp = new MimeMultipart();

        MimeBodyPart contentMbp = null;

        MimeBodyPart attachMbp = null;

        Properties props = new Properties();


        try
        {
            props.put(KEY_SMTP_HOST, mailDto.getSmtpHost());

            props.put(KEY_SMTP_PORT, mailDto.getSmtpPort());

            if (mailDto.isUseSSL()) {
                props.put(KEY_SMTP_SSL_USE, String.valueOf(mailDto.isUseSSL()));
                props.put(KEY_SMTP_SSL_AUTH_HOST, mailDto.getSmtpHost());
            }

            Authenticator authenticator = null;

            if (mailDto.isUseAuth()) {
                props.put(KEY_SMTP_AUTH_USE, String.valueOf(mailDto.isUseAuth()));

                authenticator = new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(mailDto.getAuthUserId(), mailDto.getAuthUserPass());
                    }
                };
            }

            Session msgSession = Session.getDefaultInstance(props, authenticator);

            MimeMessage msg = new MimeMessage(msgSession);

            InternetAddress fromInfo = new InternetAddress(mailDto.getFromMailAddr(), mailDto.getFromMailName(), "UTF-8");

            msg.setFrom(fromInfo);

            for (String toUser : mailDto.getToMailUserMap().keySet()) {
                InternetAddress toInfo = new InternetAddress(mailDto.getToMailUserMap().get(toUser), toUser, "UTF-8");
                msg.addRecipient(Message.RecipientType.TO, toInfo);
            }

            if (mailDto.getCcMailUserMap() != null) {
                for (String ccUser : mailDto.getCcMailUserMap().keySet()) {
                    InternetAddress ccInfo = new InternetAddress(mailDto.getCcMailUserMap().get(ccUser), ccUser, "UTF-8");
                    msg.addRecipient(Message.RecipientType.CC, ccInfo);
                }
            }

            String mailTitle = mailDto.getMailTitle();

            if (mailDto.getReplaceWord() != null && mailDto.getReplaceWord().size() != 0) {
                Map<String, String> replaceWordMap = mailDto.getReplaceWord();

                for (String rWord : replaceWordMap.keySet()) {
                    mailTitle = mailTitle.replace(rWord, replaceWordMap.get(rWord));
                }
            }

            msg.setSubject(mailTitle, "UTF-8");

            contentMbp = new MimeBodyPart();

            String mailContent = mailDto.getMailContent();

            if (mailDto.getReplaceWord() != null && mailDto.getReplaceWord().size() != 0) {
                Map<String, String> replaceWordMap = mailDto.getReplaceWord();

                for (String rWord : replaceWordMap.keySet()) {
                    log.info(rWord);
                    mailContent = mailContent.replace(rWord, replaceWordMap.get(rWord));
                }
            }

            contentMbp.setContent(mailContent, "text/html; charset=utf-8");

            mp.addBodyPart(contentMbp);

            if (mailDto.getAttachFile() != null || (mailDto.getAttachFilePath() != null && mailDto.getAttachFilePath().isEmpty() == false)) {
                attachMbp = new MimeBodyPart();

                File file;

                if ((mailDto.getAttachFilePath() != null && mailDto.getAttachFilePath().isEmpty() == false)) {
                    file = new File(mailDto.getAttachFilePath());
                } else {
                    file = mailDto.getAttachFile();
                }

                fis = new FileInputStream(file);

                int data = 0;

                List<Byte> byteArray = new ArrayList<Byte>();

                while ((data = fis.read()) != -1) {
                    byteArray.add((byte) data);
                }

                byte[] bytes = new byte[byteArray.size()];

                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = byteArray.get(i);
                }

                String attachContent = new String(bytes, "UTF-8");

                if (mailDto.getReplaceWord() != null && mailDto.getReplaceWord().size() != 0) {
                    Map<String, String> replaceWordMap = mailDto.getReplaceWord();

                    for (String rWord : replaceWordMap.keySet()) {
                        attachContent = attachContent.replace(rWord, replaceWordMap.get(rWord));
                    }
                }

                DataSource ds = new ByteArrayDataSource(attachContent.getBytes(Charset.forName("UTF-8")), "text/html");

                attachMbp.setDataHandler(new DataHandler(ds));

                attachMbp.setFileName(file.getName());

                mp.addBodyPart(attachMbp);
            }

            msg.setContent(mp);

            Transport.send(msg);


            for (String toUser : mailDto.getToMailUserMap().keySet()) {
                log.info("Mail Send " + toUser + "Complete");
            }
        } catch (Exception ex) {
            for (String toUser : mailDto.getToMailUserMap().keySet()) {
                log.info("Mail Send " + toUser + "Fail");
            }

            log.error(ex.getMessage(), ex);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioEx) {
                log.error(ioEx.getMessage(), ioEx);
            }

            log.info("Mail Send End");
        }
    }
}
