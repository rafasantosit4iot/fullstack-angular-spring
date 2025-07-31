import { ApiResponseBase } from "../../../shared/models/response";
import { BookCopySummaryItem } from "../../book-copy/models/book-copy";
import { UserSummaryItem } from "../../user/models/user";

enum LoanStatus {
    ACTIVE = 'Ativo',
    RETURNED = 'Devolvido',
    OVERDUE = 'Em atraso',
}

export interface LoanToUserSummaryItem {
    id: string;
    copyClassificationCode: string;
    bookCopyTitle: string;
    loanDate: Date;
    dueDate: Date;
    fineAmount: number;
    status: LoanStatus;
}

export interface LoanToCopySummaryItem {
    id: string;
    userName: string;
    loanDate: Date;
    dueDate: Date;
    status: LoanStatus;
}

export interface LoanResponseItem {
    id: string;
    loanDate: Date;
    dueDate: Date;
    returnDate: Date | null;
    fineAmount: number;
    status: LoanStatus;
    bookCopy: BookCopySummaryItem;
    user: UserSummaryItem;
}

export interface LoanPageResponse extends ApiResponseBase{
    content: LoanResponseItem[];
}

export interface LoanCreateBody {
    bookCopyId: string;
    userId: string;
    reservationId: string | null;
}