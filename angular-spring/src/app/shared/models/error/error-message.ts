export interface CustomErrorMessage {
    cause: {
        objectName: string;
        field: string;
        rejectedValue: string | null;
        codes: string[];
        arguments: [
            {
                codes: string[];
                arguments: string[] | null;
                defaultMessage: string;
                code: string;
            }
        ];
        defaultMessage: string;
        bindingFailure: boolean;
        code: string;
    };
    arguments: [
        {
            codes: string[];
            arguments: string[] | null;
            defaultMessage: string;
            code: string;
        }
    ];
    codes: string[];
    defaultMessage: string;
}