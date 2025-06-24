package tech.buildrun.adapter.in.web.dto;

import java.util.List;

public record ApiResponse<T>(List<T> data, String nextToken) {
}
