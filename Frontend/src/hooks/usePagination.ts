import { useState, useCallback } from "react";

export interface PaginationState {
  page: number;
  size: number;
  totalPages: number;
  totalElements: number;
}

export interface PaginatedResponse<T> {
  content: T[];
  pageable: {
    pageNumber: number;
    pageSize: number;
  };
  totalPages: number;
  totalElements: number;
  number: number;
  size: number;
  first: boolean;
  last: boolean;
}

export function usePagination<T>(initialSize = 10) {
  const [pagination, setPagination] = useState<PaginationState>({
    page: 0,
    size: initialSize,
    totalPages: 0,
    totalElements: 0,
  });

  const setPage = useCallback((page: number) => {
    setPagination((prev) => ({ ...prev, page }));
  }, []);

  const setSize = useCallback((size: number) => {
    setPagination((prev) => ({ ...prev, page: 0, size }));
  }, []);

  const updatePaginationInfo = useCallback((response: PaginatedResponse<T>) => {
    setPagination((prev) => ({
      ...prev,
      totalPages: response.totalPages,
      totalElements: response.totalElements,
    }));
  }, []);

  return {
    ...pagination,
    setPage,
    setSize,
    updatePaginationInfo,
  };
}
