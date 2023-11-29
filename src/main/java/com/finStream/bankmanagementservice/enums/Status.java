package com.finStream.bankmanagementservice.enums;

/**
 * The Status enum defines a set of possible states or statuses that can be associated
 * with Bank. These statuses represent the current condition or
 * situation of Bank.
 *
 * Possible Status Values:
 * - ACTIVE: Represents an entity that is currently active or operational.
 * - INACTIVE: Indicates that an entity is currently inactive or not in use.
 * - SUSPENDED: Suggests that an entity has been temporarily suspended or paused.
 * - UNDER_REVIEW: Denotes that an entity is currently under review or assessment.
 * - PENDING_APPROVAL: Indicates that an entity is awaiting approval or authorization.
 * - CLOSED: Represents an entity that has been closed or terminated.
 * - MAINTENANCE: Indicates that an entity is undergoing maintenance or updates.
 *
 * These statuses can be used to track and manage the state of various application entities.
 */

public enum Status {
    ACTIVE,
    INACTIVE,
    SUSPENDED,
    UNDER_REVIEW,
    PENDING_APPROVAL,
    CLOSED,
    MAINTENANCE
}
