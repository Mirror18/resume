package com.mirror.match;

import java.math.BigDecimal;

public record OrderKey(long sequenceId, BigDecimal price) {
}
