package secuwow.MET.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import secuwow.MET.domain.ScenarioInfo;
import secuwow.MET.domain.UserInfo;
import secuwow.MET.dto.ScenarioDto;
import secuwow.MET.repository.ScenarioInfoRepository;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScenarioService {

    private final ScenarioInfoRepository scenarioInfoRepository;

    @Transactional
    public void save(ScenarioInfo scenarioInfo) {
        scenarioInfoRepository.save(scenarioInfo);
    }

    public List<ScenarioInfo> findAll() {
        return scenarioInfoRepository.getScenarioList();
    }

    public ScenarioDto findScenarioInfoByScenarioId(Long scenarioId) throws UnsupportedEncodingException {
        ScenarioInfo scenarioInfo = scenarioInfoRepository.findOne(scenarioId);
        ScenarioDto scenarioDto = new ScenarioDto(scenarioInfo);
        scenarioDto.setMailContent(URLDecoder.decode(scenarioDto.getMailContent(), "UTF-8"));
        
        return scenarioDto;
    }
}
