package com.mirror.model.quotation;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import com.mirror.model.support.AbstractBarEntity;

/**
 * Store bars of day.
 */
@Entity
@Table(name = "day_bars")
public class DayBarEntity extends AbstractBarEntity {

}
