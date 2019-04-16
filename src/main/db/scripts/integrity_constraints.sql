use hotello;
# ============================= INTEGRITY CONSTRAINT FOR reservation table ================
DROP FUNCTION IF EXISTS intersection_amount;
CREATE FUNCTION intersection_amount(order_id_p INT) RETURNS BOOL
    READS SQL DATA
BEGIN
    DECLARE check_in_p DATE;
    DECLARE check_out_p DATE;
    DECLARE amount LONG;

    SELECT check_in, check_out INTO check_in_p, check_out_p FROM orders WHERE id_order = order_id_p;
    SELECT COUNT(*) INTO amount
    FROM reservations r
             INNER JOIN orders o on r.order_id = o.id_order
    WHERE (check_in_p BETWEEN o.check_in AND o.check_out)
       OR (o.check_in BETWEEN check_in_p AND check_out_p);

    RETURN amount;
END;
/*
creating check constraint
using triggers instead
    ALTER TABLE reservations
        ADD CONSTRAINT CHK_NO_RESERVATION_INTERVAL_INTERSECTIONS CHECK ( intersection_amount(order_id) = 0 )
because mysql community does not support check constraints
  */
DROP PROCEDURE IF EXISTS check_no_interval_intersections;
CREATE PROCEDURE check_no_interval_intersections(IN order_id_p INT)
BEGIN
    IF intersection_amount(order_id_p) != 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'check constraint on reservations failed';
    END IF;
END;
DROP TRIGGER IF EXISTS reservations_before_insert;
CREATE TRIGGER reservations_before_insert
    BEFORE INSERT
    ON reservations
    FOR EACH ROW
BEGIN
    CALL check_no_interval_intersections(new.order_id);
END;
DROP TRIGGER IF EXISTS reservations_before_update;
CREATE TRIGGER reservations_before_update
    BEFORE UPDATE
    ON reservations
    FOR EACH ROW
BEGIN
    CALL check_no_interval_intersections(new.order_id);
END;