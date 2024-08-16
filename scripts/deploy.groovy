def deploy() {
    unstash 'app'
    unstash 'dll'
    sshagent(credentials : ['cprod-login']) {
        sh '''
        [ -d ~/.ssh ] || mkdir ~/.ssh && chmod 0700 ~/.ssh
        ssh-keyscan -t ed25519 cprod.libvirt >> ~/.ssh/known_hosts
        ssh produser@cprod.libvirt "mkdir -p /home/produser/bin/"
        scp main-linux-release produser@cprod.libvirt:/home/produser/
        scp build/linux/release/*.so produser@cprod.libvirt:/home/produser/bin/
        '''
    }
}
return this