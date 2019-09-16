'use strict';

import React from 'react';
import UpdateForm from './UpdateForm';
import UpdateHead from './UpdateHead';

class UpdatePage extends React.Component {
    render() {
        const handleUpdate = payload => this.props.actions.updateTodo(payload);
        return (
            <div>
                <UpdateHead/>
                <UpdateForm handleUpdate={handleUpdate} handleTodoLink={this.props.handleTodoLink} />
            </div>
            );
    }
}

export default UpdatePage;
