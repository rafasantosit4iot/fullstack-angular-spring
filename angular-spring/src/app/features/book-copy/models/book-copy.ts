import { ApiResponseBase } from "../../../shared/models/response";
import { BookSummaryItem } from "../../book/models/book";
import { LoanToCopySummaryItem } from "../../loan/models/loan";

enum BookCopyStatus {
    AVAILABLE = 'Disponível',
    LOANED = 'Esprestado',
    RESERVED = 'Reservado',
    UNDER_MAINTENANCE = 'Em manutenção'
}

export interface BookCopySummaryItem {
    id: string;
    classificationCode: string;
    bookTitle: String;
    status: BookCopyStatus;
}

export interface BookCopyResponseItem {
    id: string;
    classificationCode: string;
    status: BookCopyStatus;
    book: BookSummaryItem;
    loans: LoanToCopySummaryItem[];
}

export interface BookCopyPageResponse extends ApiResponseBase{
    content: BookCopyResponseItem[];
}

export interface BookCopyCreateBody {
    bookId: string;
}