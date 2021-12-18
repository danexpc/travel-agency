package com.danexpc.agency.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Pagination {
    Integer limit;
    Integer offset;
}
