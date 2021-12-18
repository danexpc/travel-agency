package com.danexpc.agency.helpers;

import com.danexpc.agency.exceptions.MissedParameterException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

public class PaginationExtractor {
    public static Pagination extractPaginationObjectFromRequest(HttpServletRequest request) {
        String offset = request.getParameter("offset");
        String limit = request.getParameter("limit");

        if (offset == null) {
            throw new MissedParameterException(String.format(MissedParameterException.PARAMETER_IS_REQUIRED, "offset"));
        } else if (limit == null) {
            throw new MissedParameterException(String.format(MissedParameterException.PARAMETER_IS_REQUIRED, "limit"));
        }

        return new Pagination(Integer.valueOf(limit), Integer.valueOf(offset));
    }
}

