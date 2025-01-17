//package umc.stockoneqback.product.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.JobParameter;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Component
//@EnableScheduling
//public class PassProductBatchScheduler {
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private PassProductBatchConfig passProductBatchConfig;
//
//    @Scheduled(cron = "0 0 10,20 * * *")
//    public void runJob() {
//        // job parameter 설정
//        Map<String, JobParameter> confMap = new HashMap<>();
//        confMap.put("time", new JobParameter(System.currentTimeMillis()));
//        JobParameters jobParameters = new JobParameters(confMap);
//
//        try {
//            jobLauncher.run(passProductBatchConfig.job(), jobParameters);
//        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
//                 | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {
//            log.error(e.getMessage());
//        }
//    }
//}
