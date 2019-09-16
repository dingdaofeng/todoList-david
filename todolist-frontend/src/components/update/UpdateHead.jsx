'use strict';

import React from 'react';
import { Menu, Icon } from 'antd';

class UpdateHead extends React.Component {
    render() {
        return (
            <Menu selectedKeys={['update']} mode="horizontal" style={{ marginBottom: 20 }}>
                <Menu.Item key="update">
                    <Icon type="edit" /> View | Update
                </Menu.Item>
            </Menu>
            );
    }
}

export default UpdateHead;
