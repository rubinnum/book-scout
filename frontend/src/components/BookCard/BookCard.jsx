import React, {useState} from 'react';
import {Card} from "react-bootstrap";
import {Rotate3D} from "lucide-react";
import "./BookCard.css"

function BookCard({book}) {
    const [isFlipped, setIsFlipped] = useState(false);

    const handleFlip = () => setIsFlipped(!isFlipped);

    return (
        <Card onClick={handleFlip} className="book-card-wrapper" style={{transform: isFlipped ? "rotateY(180deg)" : "rotateY(0deg)"}}>
            <Card.Body className="d-flex align-items-center justify-content-center book-card-body">
                <Rotate3D className="position-absolute top-0 end-0 m-2 text-muted" size={24} />
                {!isFlipped ? (
                    <Card.Text className="mt-3">{book.description}</Card.Text>
                ) : (
                    <div className="text-center" style={{ transform: 'rotateY(180deg)' }}>
                        <Card.Img variant="top" src={book.thumbnail} alt={book.title} className="book-card-img mb-3"/>
                        <Card.Title className="p-3">{book.title}</Card.Title>
                        <Card.Subtitle className="mb-2 text-secondary">{book.author}</Card.Subtitle>
                        {book.publishedDate && <Card.Subtitle className="mb-2 text-secondary">{book.publishedDate}</Card.Subtitle>}
                        {book.pageCount && <Card.Subtitle className="text-secondary">{book.pageCount + " pages"}</Card.Subtitle>}
                    </div>
                )}
            </Card.Body>
        </Card>
    );
}

export default BookCard;