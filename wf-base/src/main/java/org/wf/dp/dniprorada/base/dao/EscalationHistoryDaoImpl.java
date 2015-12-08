package org.wf.dp.dniprorada.base.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Repository;
import org.wf.dp.dniprorada.base.model.EscalationHistory;
import java.util.List;

/**
 * @author NickVeremeichyk
 * @since 2015-12-05.
 */
@Repository
public class EscalationHistoryDaoImpl extends GenericEntityDao<EscalationHistory> implements EscalationHistoryDao {
    private static final int MAX_ROWS = 5000;
    private static final int DEFAULT_ROWS = 100;
    public EscalationHistoryDaoImpl() {
        super(EscalationHistory.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EscalationHistory> getAllByCriteria(Long nIdProcess, Long nIdProcessRoot, Long nIdUserTask, DateTime sDateStart, DateTime sDateEnd, Integer nRowsMax) {
        Criteria criteria = getSession().createCriteria(EscalationHistory.class);
        if (nIdProcess != null) {
            criteria.add(Restrictions.eq("nIdProcess", nIdProcess));
        }
        if (nIdProcessRoot != null) {
            criteria.add(Restrictions.eq("nIdProcessRoot", nIdProcessRoot));
        }
        if (nIdUserTask != null) {
            criteria.add(Restrictions.eq("nIdUserTask", nIdUserTask));
        }
        if (sDateStart != null) {
            criteria.add(Restrictions.gt("sDate", sDateStart));
        }
        if (sDateEnd != null) {
            criteria.add(Restrictions.lt("sDate", sDateEnd));
        }
        if (nRowsMax != null && nRowsMax < MAX_ROWS) {
            criteria.setMaxResults(nRowsMax);
        } else if (nRowsMax != null && nRowsMax > MAX_ROWS) {
            criteria.setMaxResults(MAX_ROWS);
        } else {
            criteria.setMaxResults(DEFAULT_ROWS);
        }

        return criteria.list();
    }
}