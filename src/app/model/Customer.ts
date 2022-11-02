export interface Customer{
    id: string,
    name: string, 
    username: string, 
    password: string,
    address: {
        zipcode: string, 
        street: string, 
        number: string, 
        neighbor: string,
        estate: string, 
    }
}