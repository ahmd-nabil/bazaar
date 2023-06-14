package nabil.bazaar.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Ahmed Nabil
 */
@Service
public class PagingService {
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 28;
    public PageRequest getPageRequest(Integer pageNumber, Integer pageSize) {
        Integer requestPageNumber = DEFAULT_PAGE_NUMBER;
        Integer requestPageSize = DEFAULT_PAGE_SIZE;
        if(pageNumber != null && pageNumber > 0) {
            requestPageNumber = pageNumber;
        }
        return PageRequest.of(requestPageNumber, requestPageSize);
    }


}
