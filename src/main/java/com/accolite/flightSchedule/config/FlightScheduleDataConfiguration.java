package com.accolite.flightSchedule.config;

import java.util.Properties;

import org.apache.kafka.connect.runtime.ConnectorConfig;
import org.apache.kafka.connect.runtime.distributed.DistributedConfig;

import com.accolite.connectionKafkaSource.ConnectionSourceKafka;

import io.confluent.connect.jdbc.JdbcSourceConnectorConfig;

public class FlightScheduleDataConfiguration {

    public ConnectionSourceKafka FlightScheduleData() throws Exception {

	Properties connectProp = new Properties();
	connectProp.put(ConnectorConfig.NAME_CONFIG, "com-accolite-risk-flightSchedule");
	connectProp.put(ConnectorConfig.CONNECTOR_CLASS_CONFIG, "io.confluent.connect.jdbc.JdbcSourceConnector");
	connectProp.put(ConnectorConfig.TASKS_MAX_DEFAULT, 10);
	connectProp.put(JdbcSourceConnectorConfig.CONNECTION_URL_CONFIG,
		"jdbc:mysql://127.0.0.1:3306/riskassesment?autoReconnect=true&useSSL=false&user=root&password=root");
	connectProp.put(JdbcSourceConnectorConfig.TABLE_WHITELIST_CONFIG, "flight_schedule");
	connectProp.put(JdbcSourceConnectorConfig.MODE_INCREMENTING, "flightScheduleId");
	connectProp.put(JdbcSourceConnectorConfig.INCREMENTING_COLUMN_NAME_DEFAULT, "flightScheduleId");
	connectProp.put(JdbcSourceConnectorConfig.TABLE_POLL_INTERVAL_MS_DEFAULT, 10000);
	connectProp.put(JdbcSourceConnectorConfig.TOPIC_PREFIX_CONFIG, "risk-");
	connectProp.put(JdbcSourceConnectorConfig.MODE_CONFIG, "incrementing");
	connectProp.put(JdbcSourceConnectorConfig.MODE_INCREMENTING, "incrementing");
	connectProp.put(JdbcSourceConnectorConfig.TIMESTAMP_DELAY_INTERVAL_MS_DEFAULT, 10000);
	connectProp.put(JdbcSourceConnectorConfig.POLL_INTERVAL_MS_CONFIG, 5000);
	connectProp.put(JdbcSourceConnectorConfig.TABLE_WHITELIST_DEFAULT, 5000);

	Properties workerProp = new Properties();
	workerProp.put(DistributedConfig.GROUP_ID_CONFIG, "RiskAssesment");
	workerProp.put(DistributedConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	workerProp.put(DistributedConfig.OFFSET_STORAGE_TOPIC_CONFIG, "riskSchedule-offset");
	workerProp.put(DistributedConfig.OFFSET_COMMIT_INTERVAL_MS_DEFAULT, 5000);

	workerProp.put(DistributedConfig.CONFIG_TOPIC_CONFIG, "riskSchedule-configs");

	workerProp.put(DistributedConfig.STATUS_STORAGE_TOPIC_CONFIG, "riskSchedule-Status");
	workerProp.put(DistributedConfig.KEY_CONVERTER_CLASS_CONFIG,
		"org.apache.kafka.connect.storage.StringConverter");
	workerProp.put(DistributedConfig.VALUE_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.json.JsonConverter");
	workerProp.put(DistributedConfig.OFFSET_COMMIT_INTERVAL_MS_DEFAULT, 5000);
	workerProp.put(DistributedConfig.OFFSET_COMMIT_INTERVAL_MS_DEFAULT, 5000);
	workerProp.put(DistributedConfig.INTERNAL_KEY_CONVERTER_CLASS_CONFIG,
		"org.apache.kafka.connect.storage.StringConverter");
	workerProp.put(DistributedConfig.INTERNAL_VALUE_CONVERTER_CLASS_CONFIG,
		"org.apache.kafka.connect.json.JsonConverter");
	workerProp.put("key.converter.schemas.enable", "false");
	workerProp.put("value.converter.schemas.enable", "false");
	workerProp.put("internal.key.converter.schemas.enable", "false");
	workerProp.put("internal.value.converter.schemas.enable", "false");
	return new ConnectionSourceKafka(workerProp, connectProp);
    }

}
