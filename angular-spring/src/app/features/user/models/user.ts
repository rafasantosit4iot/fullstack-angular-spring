import { LoanToUserSummaryItem } from "../../loan/models/loan";
import { ReservationSummaryItem } from "../../reservation/models/reservation";

export interface UserSummaryItem {
    id: string;
    name: string;
    email: string;
    libraryUserCode: string;
}

interface UserResponseItem {
    id: string;
    name: string;
    email: string;
    libraryUserCode: string;
    active: boolean;
    loans: LoanToUserSummaryItem[];
    reservations: ReservationSummaryItem[];
}