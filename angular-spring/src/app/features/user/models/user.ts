import { ApiResponseBase } from "../../../shared/models/response";
import { LoanToUserSummaryItem } from "../../loan/models/loan";
import { ReservationSummaryItem } from "../../reservation/models/reservation";

export interface UserSummaryItem {
    id: string;
    name: string;
    email: string;
    libraryUserCode: string;
}

export interface UserResponseItem {
    id: string;
    name: string;
    email: string;
    libraryUserCode: string;
    active: boolean;
    loans: LoanToUserSummaryItem[];
    reservations: ReservationSummaryItem[];
}

export interface UserPageResponse extends ApiResponseBase{
    content: UserResponseItem;
}

export interface UserCreateBody {
    name: string;
    email: string;
    maxActiveLoans: number;
    active: boolean;
}