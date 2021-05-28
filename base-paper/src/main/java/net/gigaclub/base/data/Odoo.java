package net.gigaclub.base.data;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Odoo {

    private String hostname;
    private String database;
    private String username;
    private String password;
    private XmlRpcClient client;
    private XmlRpcClientConfigImpl common_config;
    private XmlRpcClient models;
    private int uid;

    public Odoo(String hostname, String database, String username, String password) {
        this.hostname = hostname;
        this.database = database;
        this.username = username;
        this.password = password;

        this.client = new XmlRpcClient();
        this.common_config = new XmlRpcClientConfigImpl();

        this.setUp();
    }

    public void setUp() {
        try {
            this.common_config.setServerURL(new URL(String.format("%s/xmlrpc/2/common", this.hostname)));
            this.client.execute(this.common_config, "version", new ArrayList<>());
            ArrayList<Object> array = new ArrayList<Object>();
            array.add(this.database);
            array.add(this.username);
            array.add(this.password);
            array.add(new ArrayList<Object>());
            this.uid = (int)client.execute(this.common_config, "authenticate", array);
            this.models = new XmlRpcClient() {{
                setConfig(new XmlRpcClientConfigImpl() {{
                    setServerURL(new URL(String.format("%s/xmlrpc/2/object", hostname)));
                }});
            }};
        } catch (XmlRpcException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public XmlRpcClient getClient() {
        return client;
    }

    public XmlRpcClientConfigImpl getCommon_config() {
        return common_config;
    }

    public List<Object> getDemo() {
        try {
            return Arrays.asList((Object[])this.models.execute("execute_kw", Arrays.asList(
                    this.database, this.uid, this.password,
                    "res.partner", "search",
                    Arrays.asList(Arrays.asList(
                            Arrays.asList("is_company", "=", true)))
            )));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
