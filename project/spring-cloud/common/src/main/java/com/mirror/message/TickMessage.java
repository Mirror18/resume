package com.mirror.message;

import java.util.List;

import com.mirror.model.quotation.TickEntity;

public class TickMessage extends AbstractMessage {

    public long sequenceId;

    public List<TickEntity> ticks;

}
