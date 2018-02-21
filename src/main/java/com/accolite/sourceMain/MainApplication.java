package com.accolite.sourceMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.accolite.connectionKafkaSource.ConnectionSourceKafka;
import com.accolite.flightSchedule.config.FlightScheduleDataConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class MainApplication {

    public static void main(String[] args) throws Exception {
	SpringApplication.run(MainApplication.class, args);
	FlightScheduleDataConfiguration sourceKafka = new FlightScheduleDataConfiguration();

	ConnectionSourceKafka sourceConnection = sourceKafka.FlightScheduleData();
	sourceConnection.start();

    }
}
