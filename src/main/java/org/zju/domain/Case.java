package org.zju.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Tao
 * @date 2023/12/07
 */
@Data
@AllArgsConstructor
public class Case {
    int position;
    int value;
    int count;
}
