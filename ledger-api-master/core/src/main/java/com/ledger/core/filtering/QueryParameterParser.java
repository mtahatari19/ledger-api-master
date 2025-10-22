package com.ledger.core.filtering;

import jakarta.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple query parameter parser for filtering.
 */
public class QueryParameterParser {

  public static List<FilterCriteria> parseFilters(MultivaluedMap<String, String> queryParams) {
    // For now, return empty list
    // TODO: Implement actual filtering logic
    return new ArrayList<>();
  }
}
