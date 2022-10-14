package egovframework.kit.linc.domain.repo.impl;

import java.time.LocalDateTime;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import egovframework.kit.linc.domain.CollaboRequest;
import egovframework.kit.linc.domain.QCollaboRequest;
import egovframework.kit.linc.domain.Status;
import egovframework.kit.linc.domain.repo.CustomCollaboRequestRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor

@Slf4j
public class CustomCollaboRequestRepositoryImpl implements CustomCollaboRequestRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CollaboRequest> findDynamicQuery(String type, Status status, LocalDateTime startDate,
                                                 LocalDateTime endDate, Pageable pageable) {


        QCollaboRequest collaboRequest = QCollaboRequest.collaboRequest;

        List<CollaboRequest> lists = queryFactory.select(collaboRequest).from(collaboRequest)
                .where(
                        typeEqual(type),
                        statusEqual(status),
                        startDateAfter(startDate),
                        endDateBefore(endDate)
                )
                .offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();

        JPQLQuery<CollaboRequest> count = queryFactory.select(collaboRequest).from(collaboRequest)
                .where(typeEqual(type), statusEqual(status), startDateAfter(startDate), endDateBefore(endDate));

        return PageableExecutionUtils.getPage(lists, pageable, count::fetchCount);
    }

    BooleanExpression typeEqual(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        return QCollaboRequest.collaboRequest.dtype.eq(type);
    }

//	BooleanExpression nameContain(String name) {
//		if (name == null || name.isEmpty()) {
//			return null;
//		}
//		return QCollaboRequest.collaboRequest.requestName.contains(name);
//	}

    BooleanExpression statusEqual(Status status) {
        if (status == null) {
            return null;
        }
        return QCollaboRequest.collaboRequest.status.eq(status);
    }

    BooleanExpression startDateAfter(LocalDateTime startTime) {
        if (startTime == null) {
            return null;
        }
        return QCollaboRequest.collaboRequest.updatedAt.goe(startTime);
    }

    BooleanExpression endDateBefore(LocalDateTime endTime) {
        if (endTime == null) {
            return null;
        }
        return QCollaboRequest.collaboRequest.updatedAt.loe(endTime);
    }
}
