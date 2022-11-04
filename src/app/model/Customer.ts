export interface Customer{
    id: string,
    name: string, 
    cpf: string, 
    email: string, 
    phone: string,
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