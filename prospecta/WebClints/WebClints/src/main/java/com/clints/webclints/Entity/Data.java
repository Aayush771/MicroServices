package com.clints.webclints.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Data {
    private Integer count;
    private List<Entry> entries;
}
