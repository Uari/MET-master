package secuwow.MET.function;

import lombok.extern.slf4j.Slf4j;
import secuwow.MET.domain.ScenarioInfo;
import secuwow.MET.domain.TrainingUserInfo;
import secuwow.MET.domain.UserInfo;
import secuwow.MET.dto.ScenarioDto;
import secuwow.MET.dto.UserDto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

@Slf4j
public class PeriodicTaskThread extends TimerTask {

    private static List<TrainingUserInfo> postMailList = new ArrayList<>();

    private PostMail postMail = new PostMail();

    @Override
    public void run()
    {
        try
        {
            int sendMailSize = postMailList.size();
            Long currentTime = System.currentTimeMillis();

            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

            log.info("현재 시간 : " + timeFormat.format(new Date(currentTime)));
            log.info("남은 작업 갯수 : " + sendMailSize);

            if(postMailList.size() > 0)
            {
                for(int i=sendMailSize; i>0; i++)
                {
                    if(Timestamp.valueOf(postMailList.get(0).getSendTime()).getTime() <= currentTime)
                    {
                        TrainingUserInfo trainingUserInfo = postMailList.get(0);

                        ScenarioInfo scenarioInfo = trainingUserInfo.getScenarioInfo();
                        ScenarioDto scenarioDto = new ScenarioDto(scenarioInfo);

                        UserInfo userInfo = trainingUserInfo.getUserInfo();
                        UserDto userDto = new UserDto(userInfo);

                        postMail.postMail(trainingUserInfo.getEncryptionCode(), scenarioDto, userDto);
                    }
                }
            }


            log.info("작업 완료");
        }
        catch(Exception ex)
        {
            log.error(ex.getMessage(), ex);
        }

    }
}
