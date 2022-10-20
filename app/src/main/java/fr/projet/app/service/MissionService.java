package fr.projet.app.service;

import fr.projet.app.model.Mission;
import fr.projet.app.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MissionService
{
    private MissionRepository missionRepository;

    public MissionService(MissionRepository missionRepository)
    {
        this.missionRepository = missionRepository;
    }
    

    @Transactional
    public Mission createMission(Mission mission)
    {
        Optional<Mission> optionalMsn = missionRepository.findAll().stream().filter(s -> s.equals(mission)).findFirst();
        if(optionalMsn.isPresent())
        {
            Mission existMsn = optionalMsn.get();
            if(existMsn != null) return existMsn;
            else return missionRepository.save(mission);
        }
        else
        {
            return missionRepository.save(mission);
        }
    }



    @Transactional
    public Mission updateMission(Mission oldMsn, Mission newMsn)
    {
        if(oldMsn.getExperiences().size() <= 1)
        {
            this.deleteMission(oldMsn.getIdMission());
        }
        Optional<Mission> optionalMsn = missionRepository.findAll().stream().filter(s -> s.equals(newMsn)).findFirst();
        if(optionalMsn.isPresent())
        {
            Mission existMsn = optionalMsn.get();
            if(existMsn != null) return existMsn;
        }
        return missionRepository.save(newMsn);
    }



    public void deleteMission(int idMission)
    {
        missionRepository.deleteById(idMission);
        System.out.println("idMsn deleted : "+idMission);
    }

    public Optional<Mission> findMissionById(int idMission)
    {
        return missionRepository.findById(idMission);
    }
}
