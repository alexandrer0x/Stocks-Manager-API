package dev.alexandrevieira.sm.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import dev.alexandrevieira.sm.config.Values;

public class Utils {
	public static LocalDateTime toLocalDateTime(Instant instant) {
		return LocalDateTime.ofInstant(instant, getZoneId());
	}
	
	public static ZoneId getZoneId() {
		return ZoneId.of(Values.ZONE_ID);
	}
}
