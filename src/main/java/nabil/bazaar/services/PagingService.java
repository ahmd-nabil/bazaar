package nabil.bazaar.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Ahmed Nabil
 */
@Service
public class PagingService {
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 28;
    public Pageable getPageRequest(Integer pageNumber, Integer pageSize) {
        int requestPageNumber = DEFAULT_PAGE_NUMBER;
        int requestPageSize = DEFAULT_PAGE_SIZE;
        if(pageNumber != null && pageNumber > 0) {
            requestPageNumber = pageNumber - 1;
        }
        if(pageSize != null && pageSize > 0) {
            requestPageSize = pageSize;
        }
        return PageRequest.of(requestPageNumber, requestPageSize);
    }


}
