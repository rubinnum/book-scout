import React, {useState} from 'react';
import {Button, Card, Container} from "react-bootstrap";

function BookCard({book, onPrevious, onNext}) {
    const [flipped, setFlipped] = useState(false);

    return (
        <Container>
            <Button className="m-3 text-center " variant="light" onClick={onPrevious}>&uarr;</Button>

            <Card className="text-center">
                <Card.Body>{book.description}</Card.Body>
            </Card>

            <Button className="m-3 text-center" variant="light" onClick={onNext}>&darr;</Button>
        </Container>
    );
}

export default BookCard;