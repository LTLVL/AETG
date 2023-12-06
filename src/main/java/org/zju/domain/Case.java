package org.zju.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Case {
    int position;
    int value;
    int count;
}
