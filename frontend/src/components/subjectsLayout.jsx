import React, {useState} from 'react';
import {Button, Col, Container, Row} from "react-bootstrap";

function SubjectsLayout({categories}) {
    const [showAll, setShowAll] = useState(false);
    const displayedCategories = showAll ? categories : categories.slice(0, 20);

    return (
        <Container className="mt-4">
            <Row>
                {displayedCategories.map((category, index) => (
                    <Col xs={6} md={3} className="mb-4" key={index}>
                        <Button variant="outline-light" className="w-100 p-3">{category}</Button>
                    </Col>
                ))}
            </Row>

            <div className="text-center">
                {
                    (
                        <Button variant="light" className="mb-4" onClick={() => setShowAll(!showAll)}>
                            {showAll ? "Show Less" : "Show More"}
                        </Button>
                    )
                }

            </div>
        </Container>
    );
}

export default SubjectsLayout;