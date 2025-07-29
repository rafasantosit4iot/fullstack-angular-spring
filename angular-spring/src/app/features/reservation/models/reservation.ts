import { BookCopySummaryItem } from "../../book-copy/models/book-copy";
import { LoanToUserSummaryItem } from "../../loan/models/loan";
import { UserSummaryItem } from "../../user/models/user";

enum ReservationStatus {
    ACTIVE = "Ativo",
    CANCELLED = "Cancelado",
    COMPLETED = "Realizado",
}

export interface ReservationSummaryItem {
    id: string;
    bookCopyTitle: string;
    userName: string;
    reservationDate: Date;
    expirationDate: Date;
    status: ReservationStatus;
    loanId: string;

}

interface ReservationResponseItem {
    id: string;
    bookCopy: BookCopySummaryItem;
    user: UserSummaryItem;
    reservationDate: Date;
    expirationDate: Date;
    status: ReservationStatus;
    loan: LoanToUserSummaryItem;
}