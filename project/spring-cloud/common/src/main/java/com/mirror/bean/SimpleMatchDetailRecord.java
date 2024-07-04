package com.mirror.bean;

import java.math.BigDecimal;

import com.mirror.enums.MatchType;

public record SimpleMatchDetailRecord(BigDecimal price, BigDecimal quantity, MatchType type) {
}
