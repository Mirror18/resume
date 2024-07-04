package com.mirror.model.quotation;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import com.mirror.model.support.AbstractBarEntity;

/**
 * Store bars of minute.
 */
@Entity
@Table(name = "min_bars")
public class MinBarEntity extends AbstractBarEntity {

}
